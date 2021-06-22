<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html" indent="yes" omit-xml-declaration="yes" />

  <xsl:template match="/albums">
    <html>
      <head>
        <title>Music Albums</title>
        <meta http-equiv="Content-type" content="text/html; charset=iso-8859-1" />
      </head>
      <body>
        <h1>Music Albums</h1>
        <xsl:for-each select="./album">
          <xsl:call-template name="outputAlbum">
            <xsl:with-param name="album" select="." />
          </xsl:call-template>
        </xsl:for-each>
      </body>
    </html>
  </xsl:template>

  <xsl:template name="outputAlbum">
    <xsl:param name="album" />
    <h2><xsl:value-of select="$album/artist" />: <xsl:value-of select="$album/title" /> (<xsl:value-of select="$album/year" />)</h2>
    <ul>
      <xsl:for-each select="$album/tracklist/track">
        <xsl:call-template name="outputTrack">
          <xsl:with-param name="track" select="." />
        </xsl:call-template>
      </xsl:for-each>
    </ul>
  </xsl:template>

  <xsl:template name="outputTrack">
    <xsl:param name="track" />
    <li>
      <b><xsl:value-of select="$track/text()" /></b>
      <i><xsl:value-of select="$track/@duration" /></i>
    </li>
  </xsl:template>
</xsl:stylesheet>
