package de.exlll.configlib.configs.yaml;

import de.exlll.configlib.Comments;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;

final class YamlComments {
    private final Comments comments;

    YamlComments(Comments comments) {
        this.comments = comments;
    }

    String classCommentsAsString() {
        List<String> classComments = comments.getClassComments();
        return commentListToString(classComments);
    }

    Map<String, String> fieldCommentAsStrings() {
        Map<String, List<String>> fieldComments = comments.getFieldComments();
        return fieldComments.entrySet().stream()
                .map(this::toStringCommentEntry)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map.Entry<String, String> toStringCommentEntry(
            Map.Entry<String, List<String>> entry
    ) {
        String fieldComments = commentListToString(entry.getValue());
        return Map.entry(entry.getKey(), fieldComments);
    }

    private String commentListToString(List<String> comments) {
        return comments.stream()
                .map(this::toCommentLine)
                .collect(joining("\n"));
    }

    private String toCommentLine(String comment) {
        return comment.isEmpty() ? "" : "# " + comment;
    }
}