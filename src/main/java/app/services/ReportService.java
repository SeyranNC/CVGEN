package app.services;

import app.models.Education;
import app.models.Work_Exp;
import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

public class ReportService {



    public void print(List<Education> edu , List<Work_Exp> xp)  {

        JasperReportBuilder reportEDU = DynamicReports.report();
        JasperReportBuilder reportXP = DynamicReports.report();
        JasperConcatenatedReportBuilder reportBuilder =DynamicReports.concatenatedReport();

        StyleBuilder boldStyle  = stl.style().bold();

        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);

        StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle) .setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

          if(edu.size()>0){

           reportEDU.columns(
                   Columns.column("Name", "name", DataTypes.stringType()),
            Columns.column("DATE", "datex", DataTypes.dateType()),
           Columns.column("Certificate ID", "cert_id", DataTypes.stringType())

           ).title(
                   cmp.text("Education").setStyle(boldCenteredStyle)
                           .setHorizontalAlignment(HorizontalAlignment.CENTER))

                   .setDataSource(edu)

                   .setColumnStyle(boldCenteredStyle)
                   .setColumnTitleStyle(columnTitleStyle);


             reportBuilder.concatenate(reportEDU);



       }
        if(xp.size()>0){

            reportXP.columns(
                    Columns.column("Name", "name", DataTypes.stringType()),
                    Columns.column("DATE FROM", "datafrom", DataTypes.dateType()),
                    Columns.column("DATE TO", "datato", DataTypes.dateType())

            ).title(
                    cmp.text(" Work Experience").setStyle(boldCenteredStyle)
                            .setHorizontalAlignment(HorizontalAlignment.CENTER))

                    .setDataSource(xp)

                    .setColumnStyle(boldCenteredStyle)
                    .setColumnTitleStyle(columnTitleStyle);



            reportBuilder.concatenate(reportXP);


        }

        try {
            /* I used random file name and path */
            File file= new File("file.pdf");
            FileOutputStream out= new FileOutputStream( file);
            reportBuilder.toPdf(out);

            out.close();

        } catch (Exception e) {
            //LOG++
            e.printStackTrace();
        }
    }





    }
