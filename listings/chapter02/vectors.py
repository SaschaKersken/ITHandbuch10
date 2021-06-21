import matplotlib.pyplot as plt

plt.xlabel('x')
plt.ylabel('y')
plt.arrow(0, 0, 4, 3, head_width=0.1)
plt.arrow(0, 0, 3, 4, head_width=0.1)
plt.text(4.1, 3.1, 'a')
plt.text(3.1, 4.1, 'b')
plt.grid()
plt.axis([-1, 6, -1, 6])
plt.axhline(linewidth=2)
plt.axvline(linewidth=2)
plt.show()
