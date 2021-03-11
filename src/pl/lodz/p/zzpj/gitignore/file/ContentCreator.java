package pl.lodz.p.zzpj.gitignore.file;

import com.esotericsoftware.minlog.Log;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import pl.lodz.p.zzpj.gitignore.webapi.GetDataInterface;
import pl.lodz.p.zzpj.gitignore.webapi.exception.FileCreationException;
import pl.lodz.p.zzpj.gitignore.webapi.model.TechnologyObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ContentCreator {

    private GetDataInterface getData;

    private Project project;

    private List<String> list;

    public ContentCreator(@Nullable Project project, GetDataInterface getData) {
        this.getData = getData;
        this.project = project;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    private String search() {
        List<TechnologyObject> technologyObjects = list.stream()
                .filter(x -> getData.getTechnologyMap().containsKey(x))
                .map(x -> getData.getTechnologyMap().get(x))
                .collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();
        technologyObjects.forEach(x -> {
            stringBuilder.append(x.getContents());
        });

        stringBuilder.append(System.getProperty("line.separator"));
        return stringBuilder.toString();
    }

    public void createGitIgnoreFile() throws FileCreationException {
        String filePath;
        try {
            filePath = project.getBasePath().concat("/.gitignore");
        } catch(NullPointerException e) {
            throw new FileCreationException(e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(search());
        } catch (IOException e) {
            throw new FileCreationException(e);
        }
        project.getBaseDir().refresh(false, true);
    }
    
}
