import tkinter as tk
import random
import os

# --- Game Config ---
CELL = 20                  # pixel size of each grid square
GRID_W, GRID_H = 30, 30    # grid size (30x30 => 600x600 px)
SPEED_MS = 120             # initial speed in milliseconds
SPEED_MIN = 60             # minimum delay (faster)
SPEED_STEP = 2             # speed up amount every few foods
FOOD_FOR_SPEEDUP = 3
BG_COLOR = "#0f172a"       # slate-900
SNAKE_COLOR = "#22c55e"    # green-500
SNAKE_HEAD = "#4ade80"     # green-400
FOOD_COLOR = "#e11d48"     # rose-600
GRID_COLOR = "#1e293b"     # slate-800
TEXT_COLOR = "#e5e7eb"     # gray-200
BORDER_COLOR = "#334155"   # slate-700
HIGH_SCORE_FILE = "snake_highscore.txt"


def load_high_score():
    try:
        with open(HIGH_SCORE_FILE, "r", encoding="utf-8") as f:
            return int(f.read().strip() or 0)
    except Exception:
        return 0


def save_high_score(score):
    try:
        with open(HIGH_SCORE_FILE, "w", encoding="utf-8") as f:
            f.write(str(score))
    except Exception:
        pass


class SnakeGame:
    def __init__(self, root):
        self.root = root
        root.title("🐍 Snake – Tkinter Edition")
        root.resizable(False, False)

        # Wrapper frame for padding
        self.wrapper = tk.Frame(root, bg=BG_COLOR)
        self.wrapper.pack(padx=12, pady=12)

        # Header with score
        self.header = tk.Frame(self.wrapper, bg=BG_COLOR)
        self.header.pack(fill="x")
        self.title_label = tk.Label(self.header, text="🐍 Snake", font=("Segoe UI", 18, "bold"), fg=TEXT_COLOR, bg=BG_COLOR)
        self.title_label.pack(side="left")
        self.score_var = tk.StringVar(value="Score: 0    High: 0    Speed: 120ms")
        self.score_label = tk.Label(self.header, textvariable=self.score_var, font=("Consolas", 12), fg=TEXT_COLOR, bg=BG_COLOR)
        self.score_label.pack(side="right")

        # Canvas with border
        self.border = tk.Frame(self.wrapper, bg=BORDER_COLOR, bd=0)
        self.border.pack(pady=(8, 6))
        self.canvas = tk.Canvas(self.border, width=GRID_W*CELL, height=GRID_H*CELL, bg=BG_COLOR, highlightthickness=0)
        self.canvas.pack(padx=2, pady=2)

        # Footer with help and controls
        self.footer = tk.Frame(self.wrapper, bg=BG_COLOR)
        self.footer.pack(fill="x")
        self.help_label = tk.Label(
            self.footer,
            text="Arrows: mover  |  P: pausa  |  R: reiniciar  |  Esc: salir",
            font=("Segoe UI", 10), fg=TEXT_COLOR, bg=BG_COLOR
        )
        self.help_label.pack(side="left")

        self.btn_restart = tk.Button(self.footer, text="Reiniciar", command=self.reset, cursor="hand2")
        self.btn_restart.pack(side="right")

        # Bind keys
        root.bind("<Up>", lambda e: self.set_direction(0, -1))
        root.bind("<Down>", lambda e: self.set_direction(0, 1))
        root.bind("<Left>", lambda e: self.set_direction(-1, 0))
        root.bind("<Right>", lambda e: self.set_direction(1, 0))
        root.bind("p", self.toggle_pause)
        root.bind("P", self.toggle_pause)
        root.bind("r", lambda e: self.reset())
        root.bind("R", lambda e: self.reset())
        root.bind("<Escape>", lambda e: root.quit())

        # Grid lines (subtle)
        for x in range(0, GRID_W*CELL, CELL):
            self.canvas.create_line(x, 0, x, GRID_H*CELL, fill=GRID_COLOR)
        for y in range(0, GRID_H*CELL, CELL):
            self.canvas.create_line(0, y, GRID_W*CELL, y, fill=GRID_COLOR)

        # Game state
        self.after_id = None
        self.high_score = load_high_score()
        self.reset()

    def reset(self):
        if self.after_id:
            self.root.after_cancel(self.after_id)
            self.after_id = None
        self.canvas.delete("seg")
        self.canvas.delete("food")
        self.canvas.delete("overlay")

        midx, midy = GRID_W // 2, GRID_H // 2
        self.snake = [(midx, midy), (midx-1, midy), (midx-2, midy)]
        self.direction = (1, 0)  # moving right
        self.pending_dir = self.direction
        self.score = 0
        self.speed = SPEED_MS
        self.food_eaten_since_speedup = 0
        self.paused = False
        self.game_over = False
        self.spawn_food()
        self.draw_all()
        self.update_score()
        self.loop()

    def update_score(self):
        self.score_var.set(f"Score: {self.score}    High: {self.high_score}    Speed: {self.speed}ms")

    def set_direction(self, dx, dy):
        # prevent reversing immediately
        if self.game_over:
            return
        cur_dx, cur_dy = self.direction
        if (dx, dy) == (-cur_dx, -cur_dy):
            return
        self.pending_dir = (dx, dy)

    def spawn_food(self):
        empty = {(x, y) for x in range(GRID_W) for y in range(GRID_H)} - set(self.snake)
        if not empty:
            return
        self.food = random.choice(list(empty))
        self.draw_food()

    def draw_food(self):
        self.canvas.delete("food")
        x, y = self.food
        x0, y0 = x*CELL, y*CELL
        self.canvas.create_oval(x0+3, y0+3, x0+CELL-3, y0+CELL-3, fill=FOOD_COLOR, width=0, tags="food")

    def draw_all(self):
        self.canvas.delete("seg")
        for i, (x, y) in enumerate(self.snake):
            x0, y0 = x*CELL, y*CELL
            color = SNAKE_HEAD if i == 0 else SNAKE_COLOR
            radius = 4 if i == 0 else 5
            self.canvas.create_rectangle(x0+1, y0+1, x0+CELL-1, y0+CELL-1, fill=color, width=0, tags="seg")
            # rounded corners hint (tiny ovals)
            self.canvas.create_oval(x0+radius, y0+radius, x0+CELL-radius, y0+CELL-radius, outline=color, width=0, tags="seg")

    def toggle_pause(self, event=None):
        if self.game_over:
            return
        self.paused = not self.paused
        if self.paused:
            self.show_overlay("PAUSA")
        else:
            self.canvas.delete("overlay")

    def show_overlay(self, text, subtext=None):
        self.canvas.delete("overlay")
        self.canvas.create_rectangle(0, 0, GRID_W*CELL, GRID_H*CELL, fill="#000000", stipple="gray25", width=0, tags="overlay")
        self.canvas.create_text(GRID_W*CELL//2, GRID_H*CELL//2 - 10, text=text, fill=TEXT_COLOR, font=("Segoe UI", 28, "bold"), tags="overlay")
        if subtext:
            self.canvas.create_text(GRID_W*CELL//2, GRID_H*CELL//2 + 18, text=subtext, fill=TEXT_COLOR, font=("Segoe UI", 12), tags="overlay")

    def loop(self):
        if not self.paused and not self.game_over:
            self.step()
        self.after_id = self.root.after(self.speed, self.loop)

    def step(self):
        # apply new direction at step start so quick turns feel responsive
        self.direction = self.pending_dir
        dx, dy = self.direction
        head_x, head_y = self.snake[0]
        new_head = (head_x + dx, head_y + dy)

        # collisions with walls
        x, y = new_head
        if not (0 <= x < GRID_W and 0 <= y < GRID_H):
            return self.end_game("¡Te chocaste con la pared!")

        # collisions with self
        if new_head in self.snake:
            return self.end_game("¡Te mordiste! 🐍")

        self.snake.insert(0, new_head)

        # food
        if new_head == self.food:
            self.score += 1
            self.food_eaten_since_speedup += 1
            if self.score > self.high_score:
                self.high_score = self.score
                save_high_score(self.high_score)
            if self.food_eaten_since_speedup >= FOOD_FOR_SPEEDUP and self.speed > SPEED_MIN:
                self.speed = max(SPEED_MIN, self.speed - SPEED_STEP)
                self.food_eaten_since_speedup = 0
            self.spawn_food()
        else:
            self.snake.pop()  # move tail

        self.draw_all()
        self.update_score()

    def end_game(self, reason):
        self.game_over = True
        self.show_overlay("GAME OVER", f"{reason}  |  R: reiniciar")


if __name__ == "__main__":
    root = tk.Tk()
    game = SnakeGame(root)
    root.mainloop()
