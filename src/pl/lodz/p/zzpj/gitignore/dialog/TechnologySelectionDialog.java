package pl.lodz.p.zzpj.gitignore.dialog;

import com.esotericsoftware.minlog.Log;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBList;
import org.jetbrains.annotations.Nullable;
import pl.lodz.p.zzpj.gitignore.webapi.GetDataInterface;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TechnologySelectionDialog extends DialogWrapper implements Dialog {

    private DefaultListModel<String> optionsModel;
    private DefaultListModel<String> selectionsModel;

    private GetDataInterface getDataInterface;

    private List<String> selectedTechnologies;


    @SuppressWarnings("DialogTitleCapitalization")
    public TechnologySelectionDialog(GetDataInterface getDataInterface) {
        super(true);
        this.getDataInterface = getDataInterface;
        setTitle("Select technologies");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel jPanel = new JPanel(new GridLayout(2, 2));

        optionsModel = new DefaultListModel<>();
        selectionsModel = new DefaultListModel<>();
        getDataInterface.getTechnologyMap().keySet().forEach(optionsModel::addElement);

        JList<String> list = new JBList<>(optionsModel);
        JList<String> list2  = new JBList<>(selectionsModel);


        JLabel label1 = new JLabel("Exclude list:");
        jPanel.add(label1, BorderLayout.CENTER);

        JLabel label2 = new JLabel("Include list:");
        jPanel.add(label2, BorderLayout.CENTER);

        jPanel.add(new JScrollPane(list));
        jPanel.add(new JScrollPane(list2));

        setChooseListListener(list);
        setSelectedListListener(list2);

        return jPanel;
    }

    private void setChooseListListener(JList<String> list) {
        list.addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting() && list.getSelectedValue() != null) {
                String value = list.getSelectedValue();
                selectionsModel.addElement(value);
                optionsModel.removeElement(value);
            }
        });
    }

    @Override
    protected void doOKAction() {
        selectedTechnologies = IntStream.range(0, selectionsModel.size())
                .mapToObj(selectionsModel::get)
                .collect(Collectors.toList());
        super.doOKAction();
    }

    private void setSelectedListListener(JList<String> list2) {
        list2.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && list2.getSelectedValue() != null) {
                String value = list2.getSelectedValue();
                optionsModel.addElement(value);
                selectionsModel.removeElement(value);
            }
        });
    }

    public List<String> getOkList() {
        return selectedTechnologies;
    }
}
