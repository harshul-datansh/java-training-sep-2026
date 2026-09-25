import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Regenerates present.html from the Markdown files.
 *
 * Run from the project root (JDK 11+ single-file launcher, nothing to compile):
 *
 *     java tools/GeneratePresent.java
 *
 * Why: browsers block fetch() of local files when a page is opened via file://,
 * so the Markdown is embedded into the HTML as a JSON array.
 *
 * Input : README.md, PRESENTER_GUIDE.md, docs/*.md (sorted by name), tools/present-template.html
 * Output: present.html
 */
public class GeneratePresent {

    private static final String PLACEHOLDER = "/*__DOCS__*/[]";

    public static void main(String[] args) throws IOException {
        Path root = Path.of(args.length > 0 ? args[0] : ".").toAbsolutePath().normalize();
        Path template = root.resolve("tools/present-template.html");
        if (!Files.exists(template)) {
            System.err.println("Cannot find " + template + ". Run this from the project root.");
            System.exit(1);
        }

        List<Path> files = new ArrayList<>();
        files.add(root.resolve("README.md"));
        files.add(root.resolve("PRESENTER_GUIDE.md"));
        try (Stream<Path> docs = Files.list(root.resolve("docs"))) {
            docs.filter(p -> p.getFileName().toString().endsWith(".md"))
                .sorted()
                .forEach(files::add);
        }

        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < files.size(); i++) {
            Path file = files.get(i);
            String markdown = Files.readString(file, StandardCharsets.UTF_8).replace("\r\n", "\n");
            String relative = root.relativize(file).toString().replace('\\', '/');
            json.append("  {\"path\": ").append(jsString(relative))
                .append(", \"title\": ").append(jsString(titleOf(markdown, relative)))
                .append(", \"markdown\": ").append(jsString(markdown))
                .append("}").append(i < files.size() - 1 ? ",\n" : "\n");
            System.out.println("embedded " + relative);
        }
        json.append("]");

        String html = Files.readString(template, StandardCharsets.UTF_8);
        if (!html.contains(PLACEHOLDER)) {
            System.err.println("Template is missing the placeholder " + PLACEHOLDER);
            System.exit(1);
        }
        html = html.replace(PLACEHOLDER, json.toString());
        Path out = root.resolve("present.html");
        Files.writeString(out, html, StandardCharsets.UTF_8);
        System.out.println("wrote " + out + " (" + files.size() + " documents)");
    }

    /** The first "# Heading" line, or the file name if there is none. */
    private static String titleOf(String markdown, String fallback) {
        return markdown.lines()
                .filter(line -> line.startsWith("# "))
                .map(line -> line.substring(2).trim())
                .findFirst()
                .orElse(fallback);
    }

    /**
     * A JSON string literal that is also safe inside an HTML script element:
     * '<' is written as a unicode escape so "</script>" in the Markdown cannot end the script.
     */
    private static String jsString(String s) {
        StringBuilder sb = new StringBuilder("\"");
        for (char c : s.toCharArray()) {
            switch (c) {
                case '"' -> sb.append("\\\"");
                case '\\' -> sb.append("\\\\");
                case '\n' -> sb.append("\\n");
                case '\r' -> sb.append("\\r");
                case '\t' -> sb.append("\\t");
                case '<' -> sb.append("\\u003c");
                case '>' -> sb.append("\\u003e");
                case '&' -> sb.append("\\u0026");
                case ' ' -> sb.append("\\u2028");
                case ' ' -> sb.append("\\u2029");
                default -> {
                    if (c < 0x20) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.append('"').toString();
    }
}
