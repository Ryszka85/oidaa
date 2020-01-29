package sample.FxGUI.tableView;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class Column<T, U> extends TableColumn {
    private TableColumn<T, U> column;

    public Column(String columnName, String columnValue) {
        this.column = new TableColumn<>(columnName);
        this.column.setCellValueFactory(new PropertyValueFactory<>(columnValue));
    }

    public TableColumn<T, U> getColumn() {
        return column;
    }

    public void setColumn(TableColumn<T, U> column) {
        this.column = column;
    }
}
