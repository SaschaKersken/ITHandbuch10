<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="comics">
    <html>
      <head>
        <title>Comics</title>
      </head>
      <body>
        <h1>Comics</h1>
        <xsl:for-each select="./comic">
          <xsl:call-template name="output-comic">
            <xsl:with-param name="content" select="." />
          </xsl:call-template>
        </xsl:for-each>
      </body>
    </html>
  </xsl:template>

  <xsl:template name="output-comic">
    <xsl:param name="content" />
    <h2>
      <xsl:value-of select="$content/series/text()" />
      #<xsl:value-of select="$content/issue/text()" />
    </h2>
    <h3>
      <xsl:value-of select="$content/title/text()" />
      <xsl:if test="$content/subtitle">
        <br />
        <xsl:value-of select="$content/subtitle/text()" />
      </xsl:if>
      <xsl:if test="$content/issue/@original">
        <br />
        (sammelt Originalausgaben
        <xsl:value-of select="$content/issue/@original" />
        )
      </xsl:if>
    </h3>
    <p>
      <b>Verlag:</b>
      <xsl:value-of select="$content/publisher/text()" />
      <br />
      <b>Format:</b>
      <xsl:value-of select="$content/format/text()" />
      <br />
      <b>Mitwirkende:</b>
      <xsl:for-each select="$content/authors/author">
        <xsl:if test="position() > 1">, </xsl:if>
        <xsl:value-of select="./text()" />
        (<xsl:value-of select="./@role" />)
      </xsl:for-each>
      <br />
      <b>Preis:</b>
      <xsl:choose>
        <xsl:when test="$content/price/@currency = 'USD'">
          <xsl:variable name="rawprice" select="$content/price/text() div 1.1" />
          <xsl:value-of select="round($rawprice * 100) div 100" /> EUR
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="$content/price/text()" />
          <xsl:value-of select="$content/price/@currency" />
        </xsl:otherwise>
      </xsl:choose>
    </p>
  </xsl:template>
</xsl:stylesheet>
