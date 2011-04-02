package org.jboss.dmr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class Base64Test {

    @Test
    public void testEncode() {
        try {
            assertEquals("QSB0", Base64.encode("A t".getBytes()));
            assertEquals("QQ==", Base64.encode("A".getBytes()));
            assertEquals("QUI=", Base64.encode("AB".getBytes()));
            assertEquals("QUJD", Base64.encode("ABC".getBytes()));
            assertEquals("YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2FiZTo=", Base64.encode("`Twas brillig, and the slithy toves Did gyre and gimble in the wabe:".getBytes()));
            assertEquals("YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2FiZQ==", Base64.encode("`Twas brillig, and the slithy toves Did gyre and gimble in the wabe".getBytes()));
            assertEquals("YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2Fi", Base64.encode("`Twas brillig, and the slithy toves Did gyre and gimble in the wab".getBytes()));
            assertEquals("YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2E=", Base64.encode("`Twas brillig, and the slithy toves Did gyre and gimble in the wa".getBytes()));
            assertEquals("YnJpbGxpZyw=", Base64.encode("brillig,".getBytes()));
            assertEquals("cmlsbGlnLA==", Base64.encode("rillig,".getBytes()));
            assertEquals("aWxsaWcs", Base64.encode("illig,".getBytes()));
            assertEquals("bGxpZyw=", Base64.encode("llig,".getBytes()));
            assertEquals("VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wcyBvdmVyIHRoZSBsYXp5IGRvZw==",
                    Base64.encode("The quick brown fox jumps over the lazy dog".getBytes()));
        } catch (final Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }

    @Test
    public void testDecode() {
        try {
            assertEquals("A t", new String(Base64.decode("QSB0")));
            assertEquals("A", new String(Base64.decode("QQ==")));
            assertEquals("AB", new String(Base64.decode("QUI=")));
            assertEquals("ABC", new String(Base64.decode("QUJD")));
            assertEquals("`Twas brillig, and the slithy toves Did gyre and gimble in the wabe:", new String(Base64.decode("YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2FiZTo=")));
            assertEquals("`Twas brillig, and the slithy toves Did gyre and gimble in the wabe", new String(Base64.decode("YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2FiZQ==")));
            assertEquals("`Twas brillig, and the slithy toves Did gyre and gimble in the wab", new String(Base64.decode("YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2Fi")));
            assertEquals("`Twas brillig, and the slithy toves Did gyre and gimble in the wa", new String(Base64.decode("YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2E=")));
            assertEquals("brillig,", new String(Base64.decode("YnJpbGxpZyw=")));
            assertEquals("rillig,", new String(Base64.decode("cmlsbGlnLA==")));
            assertEquals("illig,", new String(Base64.decode("aWxsaWcs")));
            assertEquals("llig,", new String(Base64.decode("bGxpZyw=")));
            assertEquals("The quick brown fox jumps over the lazy dog",
                    new String(Base64.decode("VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wcyBvdmVyIHRoZSBsYXp5IGRvZw==")));
        } catch (final Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }
}
