package net.chrupki.request;

public record PatchRequest(
        String name,
        String type,
        int vid
) {}