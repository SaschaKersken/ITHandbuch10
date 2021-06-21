import lxml.etree as ET
from sys import argv, exit

if len(argv) < 4:
    print(f"Verwendung: python3 {argv[0]} XML-Quelle XSL-Stylesheet Ausgabedatei")
    exit()
xml_filename = argv[1]
xsl_filename = argv[2]
out_filename = argv[3]
dom = ET.parse(xml_filename)
xslt = ET.parse(xsl_filename)
transform = ET.XSLT(xslt)
newdom = transform(dom)
output = ET.tostring(newdom, pretty_print=True)
outfile = open(out_filename, 'wb')
outfile.write(output)
