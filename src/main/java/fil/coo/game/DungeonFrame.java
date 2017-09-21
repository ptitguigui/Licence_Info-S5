package fil.coo.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import fil.coo.controller.Direction;

public class DungeonFrame extends JFrame {

	private static final int roomHeight = 50;
	private static final int roomWidth = 50;

	private static final int borderHeight = 50;
	private static final int borderWidth = 50;

	private final Dimension panelDim;

	private Room[][] dungeon;

	public DungeonFrame(Dungeon dungeon) {
		this.dungeon = dungeon.getDungeon();

		int panelHeight = (roomHeight * this.dungeon.length) + (2 * borderHeight);
		int panelWidth = (roomWidth * this.dungeon[0].length) + (2 * borderWidth);
		panelDim = new Dimension(panelWidth, panelHeight);

		// DrawPane
		DrawPane panel = new DrawPane();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(panelDim);

		// JFRAME
		getContentPane().add(panel);
		setTitle("Dungeon");
		setPreferredSize(panelDim);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private class DrawPane extends JPanel {
		@Override
		public void paintComponent(Graphics gg) {
			super.paintComponents(gg);

			Graphics2D g = (Graphics2D) gg.create();
			g.setColor(Color.BLUE);
			g.setStroke(new BasicStroke(2));

			paintEdges(g);
			paintInside(g);

			g.setColor(Color.BLUE);
			g.setStroke(new BasicStroke(2));
			for (int y = 0; y < dungeon.length; y++) {
				for (int x = 0; x < dungeon[0].length; x++) {

					int startX = borderWidth + (roomWidth * x);
					int startY = borderHeight + (roomHeight * y);

					g.drawString(x + ", " + y, startX + 5, startY + 15);

					if (!dungeon[y][x].hasLinkedNeighbourForDirection(Direction.NORTH)) {
						g.drawLine(startX, startY, startX + roomWidth, startY);
					}
					if (!dungeon[y][x].hasLinkedNeighbourForDirection(Direction.EAST)) {
						g.drawLine(startX + roomWidth, startY, startX + roomWidth, startY + roomHeight);
					}
					if (!dungeon[y][x].hasLinkedNeighbourForDirection(Direction.SOUTH)) {
						g.drawLine(startX, startY + roomHeight, startX + roomWidth, startY + roomHeight);
					}
					if (!dungeon[y][x].hasLinkedNeighbourForDirection(Direction.WEST)) {
						g.drawLine(startX, startY, startX, startY + roomHeight);
					}
				}
			}
		}

		private void paintInside(Graphics2D g) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(borderWidth, borderHeight, roomWidth * dungeon[0].length, roomHeight * dungeon.length);
		}

		private void paintEdges(Graphics2D g) {
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(2));
			g.drawRect(0, 0, panelDim.width, panelDim.height);
		}
	}

	/*public static void main(String[] args) {
		Dungeon dungeon = new Dungeon(10);
		dungeon.initializeDungeon();
		DungeonFrame frame = new DungeonFrame(dungeon);
	}*/
}
