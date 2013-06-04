<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
  
 <ul> </ul>

 
 <xsl:for-each select="rss/channel/item">
 <li>
<a>
<xsl:attribute name="href">
<xsl:value-of select="link"/>
</xsl:attribute>
<xsl:value-of select="title"/> 
</a>
</li>
    </xsl:for-each>
  
</xsl:template>
</xsl:stylesheet>