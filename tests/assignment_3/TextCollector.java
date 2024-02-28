package assignment_3;

import java.io.IOException;
import java.io.OutputStream;

public class TextCollector extends OutputStream {
    private StringBuilder buffer = new StringBuilder();
    private OutputStream toDecorate;

    public TextCollector(OutputStream toDecorate) {
        this.toDecorate = toDecorate;
    }

    @Override
    public void write(int b) throws IOException {
        buffer.append((char) b);
        this.toDecorate.write(b);
    }

    public String collect() {
        String res = buffer.toString().replace("\r", "");
        buffer.setLength(0);
        return res;
    }
}