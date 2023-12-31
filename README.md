<p align="center">
    <img width="300" alt="Logo" src="pic/logo.png">
</p>
<h1 align="center">Forester IntelliJ Plugin</h1>


<p>
  <img width="250" alt="" src="pic/folding_l.png">
  <img width="250" alt="" src="pic/syntax_d.png">
  <img width="250" alt="" src="pic/syntax_l.png">
  <img width="250" alt="" src="pic/run_cfg_d.png">
</p>


## Introduction

## Table of Contents

- [Installation](#installation)
- [Features](#features)
  - [Syntax Highlighting](#syntax-highlighting)
  - [Folding](#folding)
  - [Structure View](#structure-view)
  - [Task Visualization](#task-visualization)
  - [Task Simulation](#task-simulation)
- [Usage](#usage)
  - [Creating a New Task](#creating-a-new-task)
  - [Configuring Task Behavior](#configuring-task-behavior)
  - [Running Task Simulations](#running-task-simulations)
- [FAQ](#faq)
- [Support](#support)
- [Changelog](#changelog)
- [License](#license)

## Installation

To install the Forester-IntelliJ Plugin, follow these simple steps:

1. Open your IntelliJ IDE.
2. Go to "Settings" or "Preferences" from the main menu.
3. Choose "Plugins" from the left-hand side menu.
4. Click on the "Marketplace" or "Browse repositories" button.
5. Search for "Forester-IntelliJ Plugin."
6. Click "Install" and restart the IDE to activate the plugin.

## Features

### Syntax Highlighting

The Forester-IntelliJ Plugin includes specialized syntax highlighting, making it easier for you to identify and distinguish Forester-related elements in your code. 
This feature helps improve code readability and ensures that your tasks are accurately represented.

### Folding

With the folding feature, you can conveniently collapse sections of your behavior trees, making complex task structures more manageable. 
Folding enhances code organization and enables you to focus on specific parts of the task tree as needed.

### Structure View

The plugin provides an intuitive Structure View that displays the hierarchical organization of your behavior trees. 
Quickly navigate through the task structure, identify parent-child relationships, and easily access specific sections of your tasks with ease.

### Task Visualization

The Forester-IntelliJ Plugin offers a task to visualize the tree that brings behavior trees to life. 
Gain valuable insights into your task flows and dependencies through interactive graphical representations. 
This visual aid fosters a better understanding of your task hierarchy, facilitating effective task organization and management.

### Task Simulation

With the task simulation feature, you can run and test your behavior trees directly within the IntelliJ IDE. 
Simulate task executions to verify their correctness and efficiency, enabling you to fine-tune your task orchestration process.

### Export to ROS Nav2

The task allows to export a given tree into ROS Nav2 xml file. 

## Usage

### Creating a New configuration

1. Navigate to the "Edit configurations" menu in your IntelliJ IDE.
2. Select "New Task" to create a new task.
3. Select one to visualize or simulate the tree.

### Running Simulation and Visualization Task

1. Open the behavior tree you want to simulate.
2. Click on the root tree gutter on the left side of the record to initiate the simulation or visualization configuration

## License

The plugin is released under the [Apache License, Version 2.0](LICENSE).

---

Please note that the above documentation assumes a basic understanding of behavior trees and task orchestration concepts. 
The provided documentation is a general template based on the given information and may require further customization 
to reflect the actual functionalities and usage of the Forester-IntelliJ Plugin.

---

[Repository](https://plugins.jetbrains.com/plugin/22387-forester)