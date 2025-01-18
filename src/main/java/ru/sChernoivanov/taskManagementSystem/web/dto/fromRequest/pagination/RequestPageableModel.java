package ru.sChernoivanov.taskManagementSystem.web.dto.fromRequest.pagination;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entity for paginating")
public class RequestPageableModel {

    @NotNull(message = "Размер страницы должен быть указан")
    @Positive(message = "Размер страницы должен быть не менее 1")
    @Schema(description = "number of tasks per page", minimum = "1")
    private Integer pageSize;

    @NotNull(message = "Номер страницы должен быть указан")
    @PositiveOrZero(message = "Номер страницы должен быть не менее 0")
    @Schema(description = "page number")
    private Integer pageNumber;

}