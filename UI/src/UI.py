from tkinter import *
from tkinter.ttk import *
from PIL import ImageTk, Image


class Window(Frame):

    def __init__(self, master=None):
        Frame.__init__(self, master)
        self.master = master
        self.init_window()

    def init_window(self):
        self.master.title("THORP")
        root.iconbitmap('supplementaryFiles/spade_shk_icon.ico')
        root.geometry("1600x800")

        # allowing the widget to take the full space of the root window
        self.pack(fill=BOTH, expand=1)

        style = Style()
        style.configure('TButton', font=('Verdana', 20),
                        borderwidth='6')


root = Tk()
app = Window(root)
im = Image.open("supplementaryFiles/imThorp.png")

img = ImageTk.PhotoImage(im.resize((int(im.size[0] * 0.75), int(im.size[1] * 0.75))))
imThorp = Label(root, image=img)
imThorp.pack(side="bottom", fill="both", expand="yes")
imThorp.place(relx=0.5, rely=0.5, anchor=CENTER)
# creating a button instance
quit_button = Button(root, text="QUIT")
# placing the button on my window
quit_button.place(relx=0.5, rely=0.8, anchor=CENTER)
root.mainloop()
