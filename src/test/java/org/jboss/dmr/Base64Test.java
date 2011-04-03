package org.jboss.dmr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
    public void testInputStreamDecode() {
        try {
            assertEquals("A t", readFromInputStream(Base64.Operations.DECODE, "QSB0"));
            assertEquals("A", readFromInputStream(Base64.Operations.DECODE, "QQ=="));
            assertEquals("AB", readFromInputStream(Base64.Operations.DECODE, "QUI="));
            assertEquals("ABC", readFromInputStream(Base64.Operations.DECODE, "QUJD"));
            assertEquals("`Twas brillig, and the slithy toves Did gyre and gimble in the wabe:", readFromInputStream(Base64.Operations.DECODE, "YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2FiZTo="));
            assertEquals("`Twas brillig, and the slithy toves Did gyre and gimble in the wabe", readFromInputStream(Base64.Operations.DECODE, "YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2FiZQ=="));
            assertEquals("`Twas brillig, and the slithy toves Did gyre and gimble in the wab", readFromInputStream(Base64.Operations.DECODE, "YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2Fi"));
            assertEquals("`Twas brillig, and the slithy toves Did gyre and gimble in the wa", readFromInputStream(Base64.Operations.DECODE, "YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2E="));
            assertEquals("brillig,", readFromInputStream(Base64.Operations.DECODE, "YnJpbGxpZyw="));
            assertEquals("rillig,", readFromInputStream(Base64.Operations.DECODE, "cmlsbGlnLA=="));
            assertEquals("illig,", readFromInputStream(Base64.Operations.DECODE, "aWxsaWcs"));
            assertEquals("llig,", readFromInputStream(Base64.Operations.DECODE, "bGxpZyw="));
            assertEquals("The quick brown fox jumps over the lazy dog", readFromInputStream(Base64.Operations.DECODE, "VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wcyBvdmVyIHRoZSBsYXp5IGRvZw=="));
        } catch (final Exception e) {
            fail("Exceptio not expected: " + e.getMessage());
        }
    }

    @Test
    public void testInputStreamEncode() {
        try {
            assertEquals("QSB0", readFromInputStream(Base64.Operations.ENCODE, "A t"));
            assertEquals("QQ==", readFromInputStream(Base64.Operations.ENCODE, "A"));
            assertEquals("QUI=", readFromInputStream(Base64.Operations.ENCODE, "AB"));
            assertEquals("QUJD", readFromInputStream(Base64.Operations.ENCODE, "ABC"));
            assertEquals("YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2FiZTo=", readFromInputStream(Base64.Operations.ENCODE, "`Twas brillig, and the slithy toves Did gyre and gimble in the wabe:"));
            assertEquals("YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2FiZQ==", readFromInputStream(Base64.Operations.ENCODE, "`Twas brillig, and the slithy toves Did gyre and gimble in the wabe"));
            assertEquals("YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2Fi", readFromInputStream(Base64.Operations.ENCODE, "`Twas brillig, and the slithy toves Did gyre and gimble in the wab"));
            assertEquals("YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2E=", readFromInputStream(Base64.Operations.ENCODE, "`Twas brillig, and the slithy toves Did gyre and gimble in the wa"));
            assertEquals("YnJpbGxpZyw=", readFromInputStream(Base64.Operations.ENCODE, "brillig,"));
            assertEquals("cmlsbGlnLA==", readFromInputStream(Base64.Operations.ENCODE, "rillig,"));
            assertEquals("aWxsaWcs", readFromInputStream(Base64.Operations.ENCODE, "illig,"));
            assertEquals("bGxpZyw=", readFromInputStream(Base64.Operations.ENCODE, "llig,"));
            assertEquals("VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wcyBvdmVyIHRoZSBsYXp5IGRvZw==", readFromInputStream(Base64.Operations.ENCODE, "The quick brown fox jumps over the lazy dog"));
        } catch (final Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }

    @Test
    public void testOutputStreamEncode() {
        try {
            assertEquals("QSB0", writeToOutputStream(Base64.Operations.ENCODE, "A t"));
            assertEquals("QQ==", writeToOutputStream(Base64.Operations.ENCODE, "A"));
            assertEquals("QUI=", writeToOutputStream(Base64.Operations.ENCODE, "AB"));
            assertEquals("QUJD", writeToOutputStream(Base64.Operations.ENCODE, "ABC"));
            assertEquals("YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2FiZTo=", writeToOutputStream(Base64.Operations.ENCODE, "`Twas brillig, and the slithy toves Did gyre and gimble in the wabe:"));
            assertEquals("YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2FiZQ==", writeToOutputStream(Base64.Operations.ENCODE, "`Twas brillig, and the slithy toves Did gyre and gimble in the wabe"));
            assertEquals("YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2Fi", writeToOutputStream(Base64.Operations.ENCODE, "`Twas brillig, and the slithy toves Did gyre and gimble in the wab"));
            assertEquals("YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2E=", writeToOutputStream(Base64.Operations.ENCODE, "`Twas brillig, and the slithy toves Did gyre and gimble in the wa"));
            assertEquals("YnJpbGxpZyw=", writeToOutputStream(Base64.Operations.ENCODE, "brillig,"));
            assertEquals("cmlsbGlnLA==", writeToOutputStream(Base64.Operations.ENCODE, "rillig,"));
            assertEquals("aWxsaWcs", writeToOutputStream(Base64.Operations.ENCODE, "illig,"));
            assertEquals("bGxpZyw=", writeToOutputStream(Base64.Operations.ENCODE, "llig,"));
            assertEquals("VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wcyBvdmVyIHRoZSBsYXp5IGRvZw==", writeToOutputStream(Base64.Operations.ENCODE, "The quick brown fox jumps over the lazy dog"));
        } catch (final Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }

    @Test
    public void testOutputStreamDecode() {
        try {
            assertEquals("A t", writeToOutputStream(Base64.Operations.DECODE, "QSB0"));
            assertEquals("A", writeToOutputStream(Base64.Operations.DECODE, "QQ=="));
            assertEquals("AB", writeToOutputStream(Base64.Operations.DECODE, "QUI="));
            assertEquals("ABC", writeToOutputStream(Base64.Operations.DECODE, "QUJD"));
            assertEquals("`Twas brillig, and the slithy toves Did gyre and gimble in the wabe:", writeToOutputStream(Base64.Operations.DECODE, "YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2FiZTo="));
            assertEquals("`Twas brillig, and the slithy toves Did gyre and gimble in the wabe", writeToOutputStream(Base64.Operations.DECODE, "YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2FiZQ=="));
            assertEquals("`Twas brillig, and the slithy toves Did gyre and gimble in the wab", writeToOutputStream(Base64.Operations.DECODE, "YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2Fi"));
            assertEquals("`Twas brillig, and the slithy toves Did gyre and gimble in the wa", writeToOutputStream(Base64.Operations.DECODE, "YFR3YXMgYnJpbGxpZywgYW5kIHRoZSBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kIGdpbWJsZSBpbiB0aGUgd2E="));
            assertEquals("brillig,", writeToOutputStream(Base64.Operations.DECODE, "YnJpbGxpZyw="));
            assertEquals("rillig,", writeToOutputStream(Base64.Operations.DECODE, "cmlsbGlnLA=="));
            assertEquals("illig,", writeToOutputStream(Base64.Operations.DECODE, "aWxsaWcs"));
            assertEquals("llig,", writeToOutputStream(Base64.Operations.DECODE, "bGxpZyw="));
            assertEquals("The quick brown fox jumps over the lazy dog", writeToOutputStream(Base64.Operations.DECODE, "VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wcyBvdmVyIHRoZSBsYXp5IGRvZw=="));
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
            assertEquals("`Twas brillig, and the slithy toves Did gyre and gimble in the wabe:", new String(Base64.decode("YFR3YXMgYnJ\npbGxpZywgYW5kIHRoZS\nBzbGl0aHkgdG92ZXMgRGlkIGd5cmUgYW5kI\nGdpbWJsZSBpbiB0aGUgd2FiZTo=")));
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

    private String readFromInputStream(final Base64.Operations operation, final String value) throws IOException {
        final StringBuilder builder = new StringBuilder();
        final Base64.InputStream is = new Base64.InputStream(new ByteArrayInputStream(value.getBytes()), operation);
        int charValue = 0;

        try {
            while(charValue != -1) {
                charValue = is.read();
                if(charValue != -1) {
                    builder.append((char)charValue);
                }
            }

            return builder.toString();
        } finally {
            is.close();
        }
    }

    private String writeToOutputStream(final Base64.Operations operation, final String value) throws IOException {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final Base64.OutputStream os = new Base64.OutputStream(bos, operation);

        os.write(value.getBytes());
        os.close();
        return new String(bos.toByteArray());
    }
}
