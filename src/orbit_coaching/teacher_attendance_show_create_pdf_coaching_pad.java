package orbit_coaching;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.Calendar;

public class teacher_attendance_show_create_pdf_coaching_pad {

    String name = "",address = "",contact_number = "";
    teacher_attendance_show_create_pdf_coaching_pad(String year,String id,String teacher_name,String institution)
    {
        try
        {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);


            String pdf_name ="PDF\\"+ Calendar.getInstance().getTime().toString()+" teachers_attendance "+year+
                    " "+id+" "+teacher_name+" coaching_pad"+".pdf";
            pdf_name=pdf_name.replace(':','_');
            //System.out.println(pdf_name);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf_name));

            document.open();

            Anchor anchorTarget = new Anchor(name, FontFactory.getFont(FontFactory.TIMES, 24, Font.BOLD));

            //anchorTarget.setName("BackToTop");
            Paragraph paragraph1 = new Paragraph();

            paragraph1.setSpacingBefore(50);
            paragraph1.add(anchorTarget);paragraph1.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph1);

            Paragraph p1 = new Paragraph("\n\n\n\n",

                    FontFactory.getFont(FontFactory.TIMES, 14));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            p1 = new Paragraph("\n",

                    FontFactory.getFont(FontFactory.TIMES, 14));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);
            p1 = new Paragraph("IDENTITY NUMBER : "+id+"\n"+"NAME : "+teacher_name+"\nINSTITUTION : "+institution
                    +"\nYEAR : "+year,
                    FontFactory.getFont(FontFactory.TIMES, 14,Font.UNDERLINE));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            PdfPTable table = new PdfPTable(3);
            table.setSpacingBefore(30);

            // the cell object
            PdfPCell cell;



            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell("SL NO.");
            table.addCell("DATE");
            table.addCell("ARRIVAL TIME");

            int counter=1;

            try {

                ResultSet resultSet = Database_query.get_teacher_attendance(id,year);

                resultSet.beforeFirst();
                while (resultSet.next()) {

                    table.addCell(Integer.toString(counter++));
                    table.addCell(resultSet.getString(1));
                    table.addCell(resultSet.getString(2));

                }
            }

            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            document.add(table);



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
