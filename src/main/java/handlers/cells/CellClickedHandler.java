package handlers.cells;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Objects;

public class CellClickedHandler extends MouseAdapter {
    private final JButton statusButton;
    private static final String MOUSE_CLICKED_PATH = "/images/surprised_face.png";
    private static final String MOUSE_RELEASED_PATH = "/images/smiling_face.png";
    public CellClickedHandler(JButton statusButton){
        this.statusButton = statusButton;
    }

    @Override
    public void mousePressed(MouseEvent event){
        //load the image
        URL clickedImageURL= getClass().getResource(MOUSE_CLICKED_PATH);
        ImageIcon clickedImageIco = new ImageIcon(Objects.requireNonNull(clickedImageURL));
        this.getStatusButton().setIcon(clickedImageIco);

    }

    @Override
    public void mouseReleased(MouseEvent event){
        URL releasedImageURL = getClass().getResource(MOUSE_RELEASED_PATH);
        ImageIcon releasedImageIco = new ImageIcon(Objects.requireNonNull(releasedImageURL));
        this.getStatusButton().setIcon(releasedImageIco);
    }



    public JButton getStatusButton(){
        return this.statusButton;
    }
}
