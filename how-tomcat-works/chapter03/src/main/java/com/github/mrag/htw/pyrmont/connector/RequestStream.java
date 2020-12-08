package com.github.mrag.htw.pyrmont.connector;

import com.github.mrag.htw.pyrmont.connector.http.Constants;
import com.github.mrag.htw.pyrmont.connector.http.HttpRequest;
import org.apache.naming.StringManager;

import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Convenience implementation of <b>ServletInputStream</b> that works with
 * the standard implementations of <b>Request</b>.  If the content length has
 * been set on our associated Request, this implementation will enforce
 * not reading more than that many bytes on the underlying stream.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.6 $ $Date: 2002/03/18 07:15:39 $
 */
public class RequestStream extends ServletInputStream {
    /**
     * 此输入流是否被关闭
     */
    protected boolean closed;
    /**
     * 此输入流已经读取了的字节数
     */
    protected int count;
    /**
     * content-length，-1 表示为未定义
     */
    protected int length;
    /**
     * 底层真实输入流，装饰模式
     */
    protected InputStream stream;

    protected static StringManager sm = StringManager.getManager(Constants.PACKAGE);

    public RequestStream(HttpRequest request) {
        super();
        closed = false;
        count = 0;
        length = request.getContentLength();
        stream = request.getStream();
    }

    /**
     * Close this input stream.  No physical level I-O is performed, but
     * any further attempt to read from this stream will throw an IOException.
     * If a content length has been set but not all of the bytes have yet been
     * consumed, the remaining bytes will be swallowed.
     */
    @Override
    public void close() throws IOException {
        if (closed)
            throw new IOException(sm.getString("requestStream.close.closed"));
        if (length > 0) {
            // 读完再关闭
            while (count < length) {
                int b = read();
                if (b < 0)
                    break;
            }
        }
        closed = true;
    }


    /**
     * Read and return a single byte from this input stream, or -1 if end of
     * file has been encountered.
     *
     * @throws IOException if an input/output error occurs
     */
    @Override
    public int read() throws IOException {
        // Has this stream been closed?
        if (closed)
            throw new IOException(sm.getString("requestStream.read.closed"));

        // Have we read the specified content length already?
        if ((length >= 0) && (count >= length))
            return (-1);        // End of file indicator

        // Read and count the next byte, then return it
        int b = stream.read();
        if (b >= 0)
            count++;
        return b;
    }


    /**
     * Read some number of bytes from the input stream, and store them
     * into the buffer array b.  The number of bytes actually read is
     * returned as an integer.  This method blocks until input data is
     * available, end of file is detected, or an exception is thrown.
     *
     * @param b The buffer into which the data is read
     * @throws IOException if an input/output error occurs
     */
    @Override
    public int read(byte[] b) throws IOException {
        return (read(b, 0, b.length));
    }


    /**
     * Read up to <code>len</code> bytes of data from the input stream
     * into an array of bytes.  An attempt is made to read as many as
     * <code>len</code> bytes, but a smaller number may be read,
     * possibly zero.  The number of bytes actually read is returned as
     * an integer.  This method blocks until input data is available,
     * end of file is detected, or an exception is thrown.
     *
     * @param b   The buffer into which the data is read
     * @param off The start offset into array <code>b</code> at which
     *            the data is written
     * @param len The maximum number of bytes to read
     * @throws IOException if an input/output error occurs
     */
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int toRead = len;
        if (length > 0) {
            if (count >= length)
                return (-1);
            if ((count + len) > length)
                toRead = length - count;
        }
        return (super.read(b, off, toRead));
    }
}
