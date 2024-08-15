package be.bram.kac;

public enum Color {

//	public abstract void visit(Visitor visitor);

	BLACK {

		public void visit(Visitor visitor) {
			visitor.visitBlack();
		}
	},
	WHITE {

		public void visit(Visitor visitor) {
			visitor.visitWhite();
		}
	};

	void visit(Visitor visitor) {

	}
}
