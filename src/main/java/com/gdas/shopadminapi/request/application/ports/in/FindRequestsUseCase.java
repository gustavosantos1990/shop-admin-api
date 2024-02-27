package com.gdas.shopadminapi.request.application.ports.in;

import com.gdas.shopadminapi.request.domain.Request;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

import static com.gdas.shopadminapi.common.domain.DateTimeUtils.firstDayOfCurrentMonth;
import static com.gdas.shopadminapi.common.domain.DateTimeUtils.lastDayOfCurrentMonth;

public interface FindRequestsUseCase extends Function<FindRequestsUseCase.Command, List<Request>> {

    record Command(LocalDate startDate, LocalDate endDate) {

        public Command(LocalDate startDate, LocalDate endDate) {
            if (startDate == null || endDate == null) {
                startDate = firstDayOfCurrentMonth();
                endDate = lastDayOfCurrentMonth();
            }
            Assert.isTrue(endDate.isAfter(startDate), "end_date must be greater than start_date");
            this.startDate = startDate;
            this.endDate = endDate;
        }

    }
}
