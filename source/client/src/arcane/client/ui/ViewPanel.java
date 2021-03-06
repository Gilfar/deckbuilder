
package arcane.client.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import arcane.client.ui.util.CardPanel;

// BOZO - Respect "scale larger" preference.
public class ViewPanel extends JPanel {
	public void layout () {
		if (getComponentCount() == 0) return;
		CardPanel panel = (CardPanel)getComponent(0);
		int viewWidth = getWidth();
		int viewHeight = getHeight();
		int srcWidth = viewWidth;
		int srcHeight = Math.round(viewWidth * CardPanel.ASPECT_RATIO);
		int targetWidth = Math.round(viewHeight * (srcWidth / (float)srcHeight));
		int targetHeight;
		if (targetWidth > viewWidth) {
			targetHeight = Math.round(viewWidth * (srcHeight / (float)srcWidth));
			targetWidth = viewWidth;
		} else
			targetHeight = viewHeight;
		int x = viewWidth / 2 - targetWidth / 2;
		int y = viewHeight / 2 - targetHeight / 2;
		panel.setCardBounds(x, y, targetWidth, targetHeight);
	}

	public void setCardPanel (CardPanel panel) {
		CardPanel newPanel = new CardPanel(panel.gameCard, false);
		newPanel.setImage(panel);
		removeAll();
		add(newPanel, BorderLayout.CENTER);
		newPanel.revalidate();
		newPanel.repaint();
	}
}
