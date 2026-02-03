package net.chrupki.request;

public record PatchRequest(
        String name,
        int tid,
        int vid
) {}