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

img = ImageTk.PhotoImage(im.resize((int(im.size[0] * 0.65), int(im.size[1] * 0.65))))
imThorp = Label(root, image=img)
imThorp.pack(side="bottom", fill="both", expand="yes")
imThorp.place(relx=0.5, rely=0.4, anchor=CENTER)
# creating a play button instance
play_button = Button(root, text="PLAY")
play_button.place(relx=0.5, rely=0.7, anchor=CENTER)
# creating INFO button instance
info_button = Button(root, text="INFO")
info_button.place(relx=0.5, rely=0.775, anchor=CENTER)
root.mainloop()
