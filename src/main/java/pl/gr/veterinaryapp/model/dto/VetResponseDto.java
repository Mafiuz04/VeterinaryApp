package pl.gr.veterinaryapp.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.gr.veterinaryapp.model.entity.Vet;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.OffsetTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class VetResponseDto extends Vet {

    @Id
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String photoUrl;
    @NotNull
    private OffsetTime workStartTime;
    @NotNull
    private OffsetTime workEndTime;
}
