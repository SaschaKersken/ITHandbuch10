class Window:
    NO_FEATURES = 0
    TITLE_BAR = 1
    CLOSE_BUTTON = 2

    def __init__(self, features = NO_FEATURES, title = '', content = ''):
        self.features = features
        self.title = title
        self.content = content

    def has_feature(self, feature):
        return self.features & feature

    def toggle_feature(self, feature):
        self.features ^= feature

    def _separator(self, width, close = False):
        result = ''
        if close and (self.features & self.CLOSE_BUTTON):
            result = '+'
            result += '-' * (width - 2)
            result += '+-+\n'
        else:
            result += '+' + (width * '-') + '+\n'
        return result

    def __str__(self):
        result = ''
        width = max(len(self.title), len(self.content))
        close_section = ''
        if width == 0:
            width = 20
        if self.features & self.CLOSE_BUTTON:
            width += 2
            close_section = '|X'
        result += self._separator(width, close = True)
        if self.features & self.TITLE_BAR:
            result += '|' + self.title
            result += (' ' * (width - len(self.title) - len(close_section)))
            result += close_section + '|\n'
            result += self._separator(width, close = True)
        result += '|'
        result += self.content + (' ' * (width - len(self.content)))
        result += '|\n'
        result += self._separator(width)
        return result

if __name__ == '__main__':
    window1 = Window(content = 'Simple Window')
    window2 = Window(
        features = Window.TITLE_BAR | Window.CLOSE_BUTTON,
        title = 'Complex Window',
        content = 'Complex Window Content'
    )
    window3 = Window(
        features = Window.TITLE_BAR | Window.CLOSE_BUTTON,
        title = 'In this window, the title is longer than the content',
        content = 'The content'
    )
    window4 = Window(
        features = Window.TITLE_BAR,
        title = 'Window without close button',
        content = 'Some content'
    )
    print(window1)
    print()
    print(window2)
    print()
    print(window3)
    print()
    print(window4)
