package orbit_coaching;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.text.TextAlignment;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.Calendar;

public class Show_student_list_pdf_school_coaching_pad {


    Show_student_list_pdf_school_coaching_pad(String cls,String yr) {
        try {

            Document document = new Document(PageSize.A4, 50, 50, 50, 50);


            String pdf_name =Calendar.getInstance().getTime().toString()+" Student List "+yr+" "+cls+"coaching_pad.pdf";
            pdf_name=pdf_name.replace(':','_');
            System.out.println(pdf_name);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf_name));

            document.open();


            //anchorTarget.setName("BackToTop");
            Paragraph paragraph1 = new Paragraph();

            paragraph1.setSpacingBefore(50);

            document.add(paragraph1);

            Paragraph p1 = new Paragraph("\n\n\n\n",

                    FontFactory.getFont(FontFactory.TIMES, 14));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            p1 = new Paragraph("\n",

                    FontFactory.getFont(FontFactory.TIMES, 14));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            p1 = new Paragraph("YEAR : "+yr+"\n"+"SCHOOL : "+cls,
                    FontFactory.getFont(FontFactory.TIMES, 14,Font.UNDERLINE));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            PdfPTable table = new PdfPTable(5);
            table.setSpacingBefore(30);

            // the cell object
            PdfPCell cell;



            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell("SL NO.");
            table.addCell("REG NO.");
            table.addCell("ROLL NO.");
            table.addCell("NAME");
            table.addCell("CLASS");


            int counter=1;
            try
            {
                ResultSet resultSet = Database_query.get_student_of_year_school(yr,cls);
                resultSet.beforeFirst();

                while (resultSet.next())
                {
                    table.addCell(Integer.toString(counter++));
                    table.addCell(resultSet.getString(1));
                    table.addCell(resultSet.getString(2));
                    table.addCell(resultSet.getString(3));
                    table.addCell(resultSet.getString(4));
                }
                resultSet.beforeFirst();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            document.add(table);




            p1.setSpacingBefore(50);
            String date_time = Calendar.getInstance().getTime().toString();
            p1 = new Paragraph("TIME : "+date_time,
                    FontFactory.getFont(FontFactory.TIMES, 14));
            document.add(p1);

            p1 = new Paragraph("\nSIGNATURE : ",
                    FontFactory.getFont(FontFactory.TIMES, 14));
            document.add(p1);

            document.close();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }
}
