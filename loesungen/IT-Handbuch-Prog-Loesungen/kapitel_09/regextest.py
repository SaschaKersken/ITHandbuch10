import re

raw_regex = input("Regulärer Ausdruck: ")
text = input("Text, auf den er angewendet werden soll: ")

regex = re.compile(raw_regex)
print(regex.findall(text))
