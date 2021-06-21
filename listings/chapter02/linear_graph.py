import math
import matplotlib.pyplot as plt
import numpy as np

x = np.linspace(-5, 5, 1000)
y = []
for x_i in x:
    y.append(2 * x_i + 3)

plt.axis([-5, 5, -7, 13])
plt.plot(x,y)
plt.title("f(x)=2x+3")
plt.xlabel('x')
plt.ylabel('y')
plt.grid()
plt.axhline(linewidth=2)
plt.axvline(linewidth=2)
plt.show()
