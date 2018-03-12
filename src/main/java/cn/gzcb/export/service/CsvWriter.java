package cn.gzcb.export.service;

/**
 * @author xiongxianwei
 * 2018/3/6
 */
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;


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
        super.write(csvLine);
        super.newLine();
    }

}