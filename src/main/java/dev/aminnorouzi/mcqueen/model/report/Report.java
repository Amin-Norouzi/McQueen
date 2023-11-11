package dev.aminnorouzi.mcqueen.model.report;

import dev.aminnorouzi.mcqueen.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    private Integer total;
    private Integer submitted;
    private Integer rejected;
    private User hero;
    private Date date;
}
