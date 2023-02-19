package org.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Модель, описывающая аватар пользователя")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvatarDto {
    @JsonProperty(Fields.UUID)
    private String uuid;

    @JsonProperty(Fields.NAME)
    private String name;

    @JsonProperty(Fields.MD5)
    private String md5;

    @JsonProperty(Fields.FILE_SIZE)
    private Long fileSize;

    @JsonProperty(Fields.BYTE_SIZE)
    private byte [] byteSize;

    public static class Fields {
        public static final String UUID = "uuid";

        private static final String NAME = "name";

        private static final String MD5 = "md5";

        private static final String FILE_SIZE = "fileSize";

        private static final String BYTE_SIZE = "byte_size";

    }

}




