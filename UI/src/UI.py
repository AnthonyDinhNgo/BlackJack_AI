from tkinter import *
from tkinter.ttk import *
from PIL import ImageTk, Image


class Window(Frame):

    def __init__(self, master=None):
        Frame.__init__(self, master)
        self.master = master
        self.init_window()

    def init_window(self):
        # changing the title of our master widget
        self.master.title("THORP")

        # allowing the widget to take the full space of the root window
        self.pack(fill=BOTH, expand=1)

        style = Style()
        style.configure('TButton', font=('Verdana', 20),
                        borderwidth='6')

        # creating a button instance
        quit_button = Button(self, text="QUIT")

        # placing the button on my window
        quit_button.place(x=0, y=0)

        canvas = Canvas(root, width=300, height=300)
        canvas.pack()
        img = ImageTk.PhotoImage(Image.open("supplementaryFiles/imThorp.png"))
        canvas.create_image(20, 20, anchor=NW, image=img)


root = Tk()
root.geometry("1600x800")
app = Window(root)
root.iconbitmap('supplementaryFiles/spade_shk_icon.ico')
root.mainloop()
