package net.chrupki.project.services.files;

import net.chrupki.dto.ProjectDTO;
import net.chrupki.project.AppProject;

import java.io.IOException;
import java.util.List;

public class ProjectService {
    public List<ProjectDTO> fetchAllProjectNames() throws IOException {
        return AppProject.FetchAllProjectNames();
    }
}
