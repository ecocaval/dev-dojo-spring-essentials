package academy.devdojo.spring.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimePutRequestBody {
    private Long id;
    private String name;
}
