package com.example.projback.wzorce.L5.Interpreter;

import com.example.projback.entity.ReservationStatus;
import com.example.projback.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//###   start L5 Interpreter -> Query Parser
public class QueryParser {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void setRepository() {
    }

    public static Expression parse(String query, ReservationRepository repository) {
        if (repository == null) {
            throw new IllegalStateException("ReservationRepository jest NULL");
        }

        if (query.contains(" AND ")) {
            String[] parts = query.split(" AND ");
            return new AndExpression(parse(parts[0].trim(), repository), parse(parts[1].trim(), repository));
        }
        if (query.contains(" OR ")) {
            String[] parts = query.split(" OR ");
            return new OrExpression(parse(parts[0].trim(), repository), parse(parts[1].trim(), repository));
        }
        if (query.startsWith("status=")) {
            return new StatusInterpreter(repository, ReservationStatus.valueOf(query.split("=")[1].trim()));
        }
        if (query.startsWith("userId=")) {
            return new UserInterpreter(repository, Long.parseLong(query.split("=")[1].trim()));
        }
        if (query.startsWith("date=")) {
            try {
                String[] dates = query.split("=")[1].trim().split(",");
                Date startDate = dateFormat.parse(dates[0].trim());
                Date endDate = dateFormat.parse(dates[1].trim());
                return new DateInterpreter(repository, startDate, endDate);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Niepoprawny format daty", e);
            }
        }
        return null;
    }
}
//###   end L5 Interpreter -> Query Parser
