package pl.lodz.p.zzpj.gitignore;
import com.intellij.ui.components.JBList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PluginFrame
{
    DefaultListModel<String> optionsModel;
    DefaultListModel<String> selectionsModel;

    public PluginFrame(List<String> keys) {
        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel(new GridLayout(2, 2));

        optionsModel = new DefaultListModel<>();
        selectionsModel = new DefaultListModel<>();
        keys.forEach(optionsModel::addElement);
        JList<String> list = new JBList<>(optionsModel);
        JList<String> list2  = new JBList<>(selectionsModel);

        JButton button = new JButton("Get selected");
        jPanel.add(new JScrollPane(list));
        jPanel.add(new JScrollPane(list2));
        jPanel.add(button);

        jFrame.setContentPane(new JScrollPane(jPanel));

        jFrame.setSize(600, 400);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jPanel.setVisible(true);
        jFrame.add(jPanel);

        setChooseListListener(list);
        setSelectedListListener(list2);
        setButtonListener(button);
    }
    private void setChooseListListener(JList<String> list) {
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectionsModel.addElement(list.getSelectedValue());
                }
            }
        });
    }

    private void setSelectedListListener(JList<String> list2) {
        list2.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String value = list2.getSelectedValue();
                    selectionsModel.removeElement(value);
                }
            }
        });
    }

    public void setButtonListener(JButton button) {
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(getSelectedList());
            }
        });
    }

    public List<String> getSelectedList() {
        return IntStream.range(0, selectionsModel.size()).mapToObj(selectionsModel::get).collect(Collectors.toList());
    }
}
