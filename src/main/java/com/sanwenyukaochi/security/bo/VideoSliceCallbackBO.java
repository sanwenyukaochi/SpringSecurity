package com.sanwenyukaochi.security.bo;

import com.sanwenyukaochi.security.entity.Clip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoSliceCallbackBO {
    private Long taskId;
    private Long videoId;
    private String videoPath;
    private String videoType;
    private List<VideoClipGroupBO> clipGroups;
    private boolean addSubtitle;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VideoClipGroupBO {
        private String start;
        private String end;
        private String summary;
        private List<VideoClipBO> clips;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VideoClipBO {
        private String start;
        private String end;
        private String topic;
        private String type;
        private List<Clip.SubtitleDTO> subtitles;
    }
}