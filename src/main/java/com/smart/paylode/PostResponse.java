package com.smart.paylode;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
    private List<PostDto> content;
    private int pageno;
    private int pagesize;
    private long totalElement;
    private int totalpages;
    private boolean lastpage;
}
