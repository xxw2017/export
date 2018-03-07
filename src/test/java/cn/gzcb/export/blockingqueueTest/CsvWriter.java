package cn.gzcb.export.blockingqueueTest;

/**
 * @author xiongxianwei
 * 2018/3/6
 */
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;


public class CsvWriter extends BufferedWriter {

    /**
     * set wirter
     *
     * @param out writer
     */
    public CsvWriter(final Writer out) {
        super(out);
    }

    /**
     * csv Write line
     *
     * @param csvLine
     *            csv line
     * @throws IOException IOException
     */
    public void writeLine(final String csvLine) throws IOException {
        //StringBuffer sb = new StringBuffer();
        /*for (int i = 0; i < csvLine.length(); i++) {
            String line = Character.toString(csvLine.charAt(i));
            if (line == null) {
                line = "";
            }
            sb.append("\"").append(line.replaceAll("\"", "\"\"")).append("\",");
        }*/

        super.write(csvLine);
        super.newLine();
    }

}