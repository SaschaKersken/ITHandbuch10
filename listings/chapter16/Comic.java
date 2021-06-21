/**
* Java representation of the following XML structure:
*
* <comic language="en-US">
*   <publisher>Marvel</publisher>
*   <series>The Amazing Spider-Man</series>
*   <format>Comic Book</format>
*   <issue>663</issue>
*   <title>The Return Of Anti-Venom</title>
*   <subtitle>Part One: The Ghost of Jean DeWolff</subtitle>
*   <authors>
*     <author role="Writer">Dan Slott</author>
*     <author role="Pencils">Giuseppe Camuncoli</author>
*   </authors>
*   <price currency="USD">3.99</price>
* </comic>
*/
public class Comic {
    private String language;
    private String publisher;
    private String series;
    private String format;
    private int issue;
    private String title;
    private String subtitle;
    private List<Author> authors;
    private String currency;
    private double price;
}
