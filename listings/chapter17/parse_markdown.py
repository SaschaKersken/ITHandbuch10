import markdown

file = open('example.md', 'r')
text = file.read()
html = markdown.markdown(text)
print(html)
