import json

json_file = open('iris.json', 'r')
try:
    irises = json.load(
        json_file,
        object_hook = lambda object: list(object.values())
    )
    json_file.close()
    print(len(irises))
    print(irises[0])
    print(irises[50])
    print(irises[100])
except json.JSONDecodeError:
    print("Ungültiges JSON")
