package net.chrupki.project.services;

import net.chrupki.project.AppProject;

import java.io.IOException;
import java.util.List;

public class ProjectService {
    public List<String> fetchAllProjectNames() throws IOException {
        return AppProject.FetchAllProjectNames();
    }
}
