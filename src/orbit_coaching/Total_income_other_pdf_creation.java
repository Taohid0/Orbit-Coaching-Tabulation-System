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

public class Total_income_other_pdf_creation {

    String name = "",address = "",contact_number = "";

    Total_income_other_pdf_creation(String yr) {
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

//            String  year =Integer.toString(Calendar.getInstance().YEAR);
//            String month =Integer.toString( Calendar.getInstance().MONTH+1);
//            String day =Integer.toString( Calendar.getInstance().DATE);
//            String hr = Integer.toString(Calendar.getInstance().HOUR);
//            String minute =Integer.toString( Calendar.getInstance().MINUTE);
//            String am_pc = "";
//
//            if (Calendar.getInstance().AM_PM==0)
//                am_pc="AM";
//            else
//                am_pc="PM";

            String pdf_name ="PDF\\"+Calendar.getInstance().getTime().toString()+" total other income.pdf";
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

            Paragraph p1 = new Paragraph("ACADEMIC/PSC/JSC/SSC/HSC Final Private Program\nAddress : "+address+"\nContact Number : "+
                    contact_number,

                    FontFactory.getFont(FontFactory.TIMES, 14));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            p1 = new Paragraph("\n",

                    FontFactory.getFont(FontFactory.TIMES, 14));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            p1 = new Paragraph("Total income of "+yr+" (From Other Sources): \n",

                    FontFactory.getFont(FontFactory.TIMES, 14,Font.UNDERLINE));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(30);

            // the cell object
            PdfPCell cell;



            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell("FROM");
            table.addCell("PURPOSE");
            table.addCell("DATE");
            table.addCell("AMOUNT");
            int total = 0;

            try
            {

                ResultSet resultSet = Database_query.get_other_income_details(yr);
                resultSet.beforeFirst();
                int counter=1;
                while (resultSet.next())
                {


                    String from = resultSet.getString(1);

                    try
                    {
                        ResultSet resultSet1 = Database_query.get_name_without_commnecttion(from);
                        resultSet1.beforeFirst();

                        if(resultSet1.next())
                        {
                            from+="("+resultSet1.getString(1)+")";
                        }
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    table.addCell(from);
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

            document.add(table);


            p1.setSpacingBefore(50);
            p1 = new Paragraph("\nTOTAL AMOUNT = "+Integer.toString(total),
                    FontFactory.getFont(FontFactory.TIMES, 14));
            document.add(p1);



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
