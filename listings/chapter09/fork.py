import os

pid = os.fork()

if pid == 0:
    print("Child process")
    for i in range(1, 10000):
        print("    Child: {}".format(i))
else:
    print("Parent process. Child: {}".format(pid))
    for i in range(1, 10000):
        print("Parent: {}".format(i))

