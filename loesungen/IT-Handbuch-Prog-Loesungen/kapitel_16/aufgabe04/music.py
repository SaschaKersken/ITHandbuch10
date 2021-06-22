from xml.etree import ElementTree

doc = ElementTree.parse('music.xml')
root = doc.getroot()
for album in root:
    print('"{}" by {}'.format(album.find('title').text, album.find('artist').text))
    for track in album.findall('.//track'):
        print('- {} ({})'.format(track.text, track.attrib['duration']))
