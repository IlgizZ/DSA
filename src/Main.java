/* while (!(x.equals(root)) && x.color == Color.BLACK){
        if (x.equals(x.parent.left)){
        Node uncle = x.parent.right;

        if (uncle.color == Color.RED){
        uncle.setColor(Color.BLACK);
        x.parent.setColor(Color.RED);
        leftRotate(x.parent);
        uncle = x.parent.right;
        }

        if (uncle.left.color == Color.BLACK && uncle.right.color == Color.BLACK){
        uncle.setColor(Color.RED);
        x = x.parent;
        } else {
        //rigth nil ??
        if (uncle.right.color == Color.BLACK){
        uncle.left.setColor(Color.BLACK);
        uncle.setColor(Color.RED);
        rightRotate(uncle);
        uncle = x.parent.right;
        }
        uncle.setColor(x.parent.color);
        x.parent.setColor(Color.BLACK);
        uncle.right.setColor(Color.BLACK);
        leftRotate(x.parent);
        x = root;
        }

        } else {

        }
        }

        x.color = Color.BLACK;
        }*/
