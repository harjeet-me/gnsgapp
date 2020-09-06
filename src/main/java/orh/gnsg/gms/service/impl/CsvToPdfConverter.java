package orh.gnsg.gms.service.impl;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CsvToPdfConverter {

    public static byte[] csvToPdfConverter(byte[] bytes, ReportObj reportObj) throws IOException, DocumentException {
        Path path = Paths.get("student.csv");

        // byte[] bytes = Files.readAllBytes(path);

        String str = new String(bytes);

        String[] splitted = Arrays.stream(str.split("\n")).map(String::trim).toArray(String[]::new);

        // System.out.println(Arrays.toString(splitted));

        List<String> list = Arrays.asList(splitted);

        System.out.println("arrays splitted " + Arrays.toString(splitted));
        System.out.println("arrays list " + Arrays.toString(splitted));
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4, 2, 2, 2, 2);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, outStream);

        document.open();

        Paragraph heading = new Paragraph(
            reportObj.getReportType() + " From  " + reportObj.getStartDate() + " To    " + reportObj.getEndDate(),
            FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD, new BaseColor(255, 0, 0))
        );

        document.add(heading);
        int headersize = 1;
        for (String record : list) {
            String[] line = Arrays.stream(record.split(",")).map(String::trim).toArray(String[]::new);

            String id = line[0];
            String dob = line[1];
            String email = line[2];
            String address = line[3];

            List<String> feildlist = Arrays.asList(line);

            headersize = feildlist.size();
            break;
        }

        PdfPTable t = new PdfPTable(headersize);
        t.setSpacingBefore(10);
        t.setSpacingAfter(10);
        // String lastrow="..,..,Report Total , "+reportObj.getReportTotal();
        //	list.add(lastrow);
        boolean isHeader = true;
        for (String record : list) {
            String[] line = Arrays.stream(record.split(",")).map(String::trim).toArray(String[]::new);

            String id = line[0];
            String dob = line[1];
            String email = line[2];
            String address = line[3];

            List<String> feildlist = Arrays.asList(line);
            System.out.println("arrays feildlist " + feildlist + "\n length  -------------------------------- " + feildlist.size());

            if (isHeader) {
                /*
                 * PdfPCell c1 = new PdfPCell(new Phrase(id)); t.addCell(c1);
                 *
                 * PdfPCell c2 = new PdfPCell(new Phrase(dob)); t.addCell(c2);
                 *
                 * PdfPCell c3 = new PdfPCell(new Phrase(email)); t.addCell(c3);
                 *
                 * PdfPCell c4 = new PdfPCell(new Phrase(address)); t.addCell(c4);
                 */

                for (String f : feildlist) {
                    PdfPCell cell = new PdfPCell(new Phrase(f));

                    t.addCell(cell);
                }
            } else {
                /*
                 * PdfPCell c1 = new PdfPCell(new Phrase(id)); t.addCell(c1);
                 *
                 * PdfPCell c2 = new PdfPCell(new Phrase(dob)); t.addCell(c2);
                 *
                 * PdfPCell c3 = new PdfPCell(new Phrase(email)); t.addCell(c3);
                 *
                 * PdfPCell c4 = new PdfPCell(new Phrase(address)); t.addCell(c4);
                 */

                for (String f : feildlist) {
                    PdfPCell cell = new PdfPCell(new Phrase(f));

                    t.addCell(cell);
                }
                /*	PdfPCell cell = new PdfPCell(new Phrase(lastrow));

				t.addCell(cell);*/

            }
        }

        document.add(t);
        Paragraph bottom = new Paragraph(
            "Report Total : " + reportObj.getReportTotal(),
            FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD, new BaseColor(255, 0, 0))
        );
        document.add(bottom);
        document.close();
        pdfWriter.close();

        return outStream.toByteArray();
    }
}
