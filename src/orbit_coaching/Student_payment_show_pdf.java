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

public class Student_payment_show_pdf {

    String name = "",address = "",contact_number = "";

    Student_payment_show_pdf(String reg,String yr,String st_name,String fname,String mname,String school) {
        try {
            try
            {
                ResultSet resultSet = Database_query.get_institution_info();
                resultSet.beforeFirst();

                while (resultSet.next())
                {
                    name = resultSet.getString(1);
                    address = resultSet.getString(2);
                    contact_number = resultSet.getString(3);
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);


            String pdf_name ="PDF\\"+Calendar.getInstance().getTime().toString()+" All payment for Student "+reg+ " "
            +yr+ ".pdf";
            pdf_name=pdf_name.replace(':','_');
            System.out.println(pdf_name);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf_name));

            document.open();

            Anchor anchorTarget = new Anchor(name,FontFactory.getFont(FontFactory.TIMES, 24, Font.BOLD));

            //anchorTarget.setName("BackToTop");
            Paragraph paragraph1 = new Paragraph();

            paragraph1.setSpacingBefore(50);
            paragraph1.add(anchorTarget);paragraph1.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph1);

            Paragraph p1 = new Paragraph("ACADEMIC/PEC/JSC/SSC/HSC Final Private Program\nAddress : "+address+"\nContact Number : "+
                    contact_number,

                    FontFactory.getFont(FontFactory.TIMES, 14));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            p1 = new Paragraph("\n",

                    FontFactory.getFont(FontFactory.TIMES, 14));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            p1 = new Paragraph("YEAR : "+yr+"\n"+"REGISTRATION NO: "+reg+"\nNAME : "+st_name+
                    "\nFATHER'S NAME : "+fname+"\nMOTHER'S NAME : "+mname+"\nINSTITUTION : "+school,
                    FontFactory.getFont(FontFactory.TIMES, 14,Font.UNDERLINE));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(30);

            // the cell object
            PdfPCell cell;



            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell("MONTH");
            table.addCell("DATE");
            table.addCell("PURPOSE");
            table.addCell("AMOUNT");



            int counter=1,total=0;
            try
            {
                ResultSet resultSet= Database_query.get_monthy_payment_data(yr,reg);
                resultSet.beforeFirst();

                while (resultSet.next())
                {
                    table.addCell(resultSet.getString(1));
                    table.addCell(resultSet.getString(2));
                    table.addCell(resultSet.getString(3));
                    table.addCell(resultSet.getString(4));
                    total+=Integer.parseInt(resultSet.getString(4));
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            try
            {
                ResultSet resultSet = Database_query.get_other_income_details_specific_student(yr,reg);
                resultSet.beforeFirst();

                while (resultSet.next())
                {
                    table.addCell("");
                    table.addCell(resultSet.getString(3));
                    table.addCell(resultSet.getString(2));
                    table.addCell(resultSet.getString(4));
                    total+=Integer.parseInt(resultSet.getString(4));
                }

            }
            catch (Exception ex)
            {
                ex.printStackTrace();

            }
            document.add(table);




            p1.setSpacingBefore(50);
            p1 = new Paragraph("TOTAL AMOUNT : "+total,
                    FontFactory.getFont(FontFactory.TIMES, 14));
            document.add(p1);
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
