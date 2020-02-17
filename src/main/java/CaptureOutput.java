import java.util.ArrayList;
import java.util.List;

public class CaptureOutput implements Writable {

    private List<String> lines;

    public CaptureOutput() {
        this.lines = new ArrayList<String>();
    }

    public List<String> getLines() {
        return lines;
    }

    @Override
    public void println(String s) {
        this.lines.add(s);
    }
}
